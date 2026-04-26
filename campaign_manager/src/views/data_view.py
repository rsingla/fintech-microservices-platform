import streamlit as st
import pandas as pd
from ..database import get_data
from ..models import CampaignData
from typing import List

def paginate_dataframe(df: pd.DataFrame, page_size: int = 10) -> tuple:
    """Helper function to paginate a DataFrame"""
    n_pages = len(df) // page_size + (1 if len(df) % page_size > 0 else 0)
    
    # Add page selector to sidebar
    page = st.sidebar.number_input(
        'Page Number:',
        min_value=1,
        max_value=n_pages,
        value=1
    )
    
    # Calculate start and end indices
    start_idx = (page - 1) * page_size
    end_idx = min(start_idx + page_size, len(df))
    
    return df.iloc[start_idx:end_idx], page, n_pages

def render_data_view():
    """Render the main data view section of the application"""
    campaign_data = get_data()

    if campaign_data:
        # Add view options in sidebar
        with st.sidebar:
            st.markdown("### View Controls")
            
            # Add search box at the top of sidebar
            search_term = st.text_input(
                "🔍 Search campaigns",
                placeholder="Enter campaign name or description"
            ).lower()
            
            st.markdown("---")
            
            # Add other controls
            st.markdown("### Filters")
            status_filter = st.multiselect(
                "Campaign Status:",
                options=list(set(camp.campaign_status for camp in campaign_data)),
                default=[]
            )
            
            st.markdown("### Display Options")
            page_size = st.select_slider(
                "Rows per page:",
                options=[10, 20, 30, 40, 50],
                value=20
            )
            
            st.markdown("### Actions")
            if st.button('🔄 Refresh Data', use_container_width=True):
                st.cache_data.clear()
                st.rerun()

        # Filter and display data
        filtered_data = filter_campaign_data(campaign_data, status_filter, search_term)
        display_campaign_data(filtered_data, page_size)
    else:
        st.info("No marketing data found. Please upload data using the Upload page.")

def filter_campaign_data(campaign_data, status_filter, search_term):
    """Filter campaign data based on selected filters"""
    filtered_data = campaign_data
    
    if status_filter:
        filtered_data = [camp for camp in filtered_data if camp.campaign_status in status_filter]
    
    if search_term:
        filtered_data = [camp for camp in filtered_data if 
                        search_term in camp.campaign_name.lower() or 
                        search_term in camp.description.lower()]
    
    return filtered_data

def display_campaign_data(filtered_data, page_size):
    """Display the filtered campaign data with pagination"""
    # Convert to DataFrame for display
    flattened_data = [flatten_campaign_data(campaign) for campaign in filtered_data]
    df = pd.DataFrame(flattened_data)
    
    if len(df) > 0:
        # Display summary metrics
        display_summary_metrics(df)
        
        # Paginate and display the data
        paginated_df, current_page, total_pages = paginate_dataframe(df, page_size)
        
        st.markdown("### Campaign List")
        st.markdown(f"*Showing page {current_page} of {total_pages} ({len(df)} total records)*")
        
        st.dataframe(
            paginated_df,
            use_container_width=True,
            hide_index=True,
        )
        
        # Add detailed view for selected campaigns
        if st.checkbox("Show Detailed Campaign Information"):
            selected_campaigns = [camp for camp in filtered_data 
                               if camp.campaign_id in paginated_df['campaign_id'].values]
            for campaign in selected_campaigns:
                with st.expander(f"Campaign: {campaign.campaign_name}"):
                    render_campaign_details(campaign)
    else:
        st.warning("No campaigns match the selected filters.")

def display_summary_metrics(df):
    """Display summary metrics at the top of the page"""
    st.markdown("### Campaign Overview")
    
    col1, col2, col3, col4 = st.columns(4)
    
    with col1:
        st.metric(
            "Total Campaigns",
            len(df)
        )
    
    with col2:
        active_campaigns = len(df[df['campaign_status'] == 'Active'])
        st.metric(
            "Active Campaigns",
            active_campaigns
        )
    
    with col3:
        avg_roi = df['roi'].str.rstrip('%').astype(float).mean()
        st.metric(
            "Average ROI",
            f"{avg_roi:.1f}%"
        )
    
    with col4:
        total_budget = df['overall_budget'].str.lstrip('$').str.replace(',', '').astype(float).sum()
        st.metric(
            "Total Budget",
            f"${total_budget:,.0f}"
        )

def render_campaign_details(campaign: CampaignData):
    """Render detailed information for a single campaign"""
    # Campaign Overview
    st.markdown("### Campaign Overview")
    col1, col2, col3 = st.columns(3)
    with col1:
        st.metric("Total Pieces Sent", f"{campaign.performance_summary.total_campaign_pieces_sent:,}")
    with col2:
        st.metric("Response Rate", f"{campaign.performance_summary.overall_response_rate:.1f}%")
    with col3:
        st.metric("ROI", f"{campaign.performance_summary.campaign_roi:.1f}%")
    
    # Render the detailed tables
    render_strategy_cells(campaign)
    render_weekly_mail_drops(campaign)
    render_cost_breakdown(campaign)

def flatten_campaign_data(campaign: CampaignData) -> dict:
    """Convert a CampaignData object into a flat dictionary"""
    return {
        "campaign_id": campaign.campaign_id,
        "campaign_name": campaign.campaign_name,
        "description": campaign.description,
        "campaign_goal": campaign.campaign_goal,
        "target_audience_criteria": campaign.target_audience_criteria,
        "overall_start_date": campaign.overall_start_date,
        "overall_end_date": campaign.overall_end_date,
        "campaign_status": campaign.campaign_status,
        "overall_budget": f"${campaign.cost_details.overall_budget:,.2f}",
        "total_cost_actual": f"${campaign.cost_details.total_campaign_cost_actual:,.2f}",
        "pieces_sent": f"{campaign.performance_summary.total_campaign_pieces_sent:,}",
        "response_rate": f"{campaign.performance_summary.overall_response_rate:.1f}%",
        "conversion_rate": f"{campaign.performance_summary.overall_conversion_rate:.1f}%",
        "roi": f"{campaign.performance_summary.campaign_roi:.1f}%"
    }

def render_strategy_cells(campaign: CampaignData):
    """Render strategy cells table"""
    st.subheader("Strategy Cells")
    cells_data = [{
        "Cell No": cell.cell_no,
        "Description": cell.cell_description,
        "Creative ID": cell.assigned_creative_id,
        "Offer Code": cell.assigned_offer_code,
        "Total Mailed": f"{cell.campaign_total_mailed:,}",
        "Total Responses": f"{cell.campaign_total_responses:,}",
        "Total Conversions": f"{cell.campaign_total_conversions:,}",
        "Conversion Value": f"${cell.campaign_total_conversion_value:,.2f}"
    } for cell in campaign.strategy_cells]
    st.dataframe(pd.DataFrame(cells_data), hide_index=True)

def render_weekly_mail_drops(campaign: CampaignData):
    """Render weekly mail drops table"""
    st.subheader("Weekly Mail Drops")
    drops_data = [{
        "Drop ID": drop.mail_drop_id,
        "Week Start": drop.mailing_week_start_date,
        "Planned Date": drop.planned_send_date,
        "Actual Date": drop.actual_send_date,
        "Pieces Sent": f"{drop.total_pieces_sent_this_week:,}"
    } for drop in campaign.weekly_mail_drops]
    st.dataframe(pd.DataFrame(drops_data), hide_index=True)

def render_cost_breakdown(campaign: CampaignData):
    """Render cost breakdown table"""
    st.subheader("Cost Breakdown")
    cost_data = {
        "Category": ["Printing", "Postage", "Data", "Other"],
        "Cost": [
            f"${campaign.cost_details.cost_breakdown.printing_cost_actual:,.2f}",
            f"${campaign.cost_details.cost_breakdown.postage_cost_actual:,.2f}",
            f"${campaign.cost_details.cost_breakdown.data_cost_actual:,.2f}",
            f"${campaign.cost_details.cost_breakdown.other_costs_actual:,.2f}"
        ]
    }
    st.dataframe(pd.DataFrame(cost_data), hide_index=True) 