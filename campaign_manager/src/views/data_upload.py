import streamlit as st
import pandas as pd
from io import BytesIO
from typing import Optional
from ..models import CampaignData, CostDetails, CostBreakdown, StrategyCell, WeeklyMailDrop, PerformanceSummary
from ..database import init_connection
from ..config import MONGO_DB_NAME, MONGO_COLLECTION_NAME

def render_upload_section():
    """Render the data upload section of the application"""
    col1, col2, col3 = st.columns([1,2,1])
    
    with col2:
        st.markdown(
            """
            <div style='text-align: center;'>
                <h2>Upload Campaign Data</h2>
                <p>Upload your campaign data file (CSV or Excel) to add it to the database.</p>
            </div>
            """,
            unsafe_allow_html=True
        )
        
        uploaded_file = st.file_uploader(
            "Choose a CSV or Excel file",
            type=["csv", "xlsx"],
            help="Upload a file containing campaign data. The file should include all required fields."
        )

        if uploaded_file is not None:
            process_uploaded_file(uploaded_file)

def process_uploaded_file(uploaded_file):
    """Process the uploaded file and insert data into MongoDB"""
    try:
        with st.spinner('Reading file...'):
            df = read_file(uploaded_file)
            
        if df is not None:
            with st.spinner('Processing data...'):
                df = preprocess_dataframe(df)
                preview_and_upload_data(df)
    except Exception as e:
        st.error('Error processing file')
        st.error(str(e))

def read_file(uploaded_file) -> Optional[pd.DataFrame]:
    """Read the uploaded file into a pandas DataFrame"""
    file_extension = uploaded_file.name.split('.')[-1].lower()
    
    if file_extension == 'csv':
        return pd.read_csv(uploaded_file)
    elif file_extension == 'xlsx':
        return pd.read_excel(BytesIO(uploaded_file.getvalue()))
    else:
        st.error("Unsupported file type. Please upload a CSV or Excel file.")
        return None

def preprocess_dataframe(df: pd.DataFrame) -> pd.DataFrame:
    """Clean and preprocess the DataFrame"""
    df.columns = df.columns.str.replace(' ', '_').str.lower()
    return df.where(pd.notnull(df), None)

def create_campaign_object(row):
    """Create a CampaignData object from a DataFrame row"""
    try:
        # Create CostBreakdown object
        cost_breakdown = CostBreakdown(
            printing_cost_actual=float(row['printing_cost_actual']),
            postage_cost_actual=float(row['postage_cost_actual']),
            data_cost_actual=float(row['data_cost_actual']),
            other_costs_actual=float(row['other_costs_actual'])
        )

        # Create CostDetails object
        cost_details = CostDetails(
            overall_budget=float(row['overall_budget']),
            total_campaign_cost_planned=float(row['total_campaign_cost_planned']),
            total_campaign_cost_actual=float(row['total_campaign_cost_actual']),
            cost_per_piece_planned=float(row['cost_per_piece_planned']),
            cost_per_piece_actual=float(row['cost_per_piece_actual']),
            cost_breakdown=cost_breakdown
        )

        # Create StrategyCell object
        strategy_cell = StrategyCell(
            cell_no=str(row['cell_no']),
            cell_description=str(row['cell_description']),
            assigned_creative_id=str(row['assigned_creative_id']),
            assigned_offer_code=str(row['assigned_offer_code']),
            campaign_total_mailed=int(row['campaign_total_mailed']),
            campaign_total_responses=int(row['campaign_total_responses']),
            campaign_total_conversions=int(row['campaign_total_conversions']),
            campaign_total_conversion_value=float(row['campaign_total_conversion_value'])
        )

        # Create WeeklyMailDrop object
        weekly_mail_drop = WeeklyMailDrop(
            mail_drop_id=str(row['mail_drop_id']),
            mailing_week_start_date=str(row['mailing_week_start_date']),
            planned_send_date=str(row['planned_send_date']),
            actual_send_date=str(row['actual_send_date']),
            total_pieces_sent_this_week=int(row['total_pieces_sent_this_week']),
            cells_mailed_this_week=[]  # Initialize empty for now
        )

        # Create PerformanceSummary object
        performance_summary = PerformanceSummary(
            total_campaign_pieces_sent=int(row['total_campaign_pieces_sent']),
            total_campaign_responses=int(row['total_campaign_responses']),
            overall_response_rate=float(row['overall_response_rate']),
            total_campaign_conversions=int(row['total_campaign_conversions']),
            overall_conversion_rate=float(row['overall_conversion_rate']),
            total_campaign_conversion_value=float(row['total_campaign_conversion_value']),
            average_conversion_value=float(row['average_conversion_value']),
            campaign_roi=float(row['campaign_roi'])
        )

        # Create CampaignData object
        campaign = CampaignData(
            campaign_id=str(row['campaign_id']),
            campaign_name=str(row['campaign_name']),
            description=str(row['description']),
            campaign_goal=str(row['campaign_goal']),
            target_audience_criteria=str(row['target_audience_criteria']),
            overall_start_date=str(row['overall_start_date']),
            overall_end_date=str(row['overall_end_date']),
            campaign_status=str(row['campaign_status']),
            cost_details=cost_details,
            strategy_cells=[strategy_cell],  # List with single cell for now
            weekly_mail_drops=[weekly_mail_drop],  # List with single drop for now
            performance_summary=performance_summary
        )

        return campaign
    except Exception as e:
        st.error(f"Error creating campaign object: {e}")
        st.error("Problematic row:", row)
        raise

def preview_and_upload_data(df: pd.DataFrame):
    """Preview the data and handle the upload to MongoDB"""
    st.markdown("### Data Preview")
    st.dataframe(
        df.head(),
        use_container_width=True,
        hide_index=True
    )
    
    col1, col2, col3 = st.columns([1,2,1])
    with col2:
        if st.button(
            "Upload to Database",
            help="Click to upload the data to the database",
            use_container_width=True
        ):
            with st.spinner('Uploading data...'):
                upload_to_mongodb(df)

def upload_to_mongodb(df: pd.DataFrame):
    """Upload the data to MongoDB"""
    try:
        client = init_connection()
        db = client[MONGO_DB_NAME]
        collection = db[MONGO_COLLECTION_NAME]

        # Convert each row to a CampaignData object
        campaigns = []
        for _, row in df.iterrows():
            try:
                campaign = create_campaign_object(row)
                campaigns.append(campaign)
            except Exception as e:
                st.error(f"Error processing row: {e}")
                continue

        # Convert to dictionaries and insert
        data_to_insert = [campaign.to_dict() for campaign in campaigns]
        
        if data_to_insert:
            insert_result = collection.insert_many(data_to_insert)
            st.success(f'Successfully uploaded {len(insert_result.inserted_ids)} records.')
            st.cache_data.clear()
        else:
            st.error("No valid data to insert")
            
    except Exception as e:
        st.error(f"Error uploading to MongoDB: {e}") 