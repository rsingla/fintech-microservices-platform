import pandas as pd
from datetime import datetime, timedelta
import random
import string

def random_date(start_year=2023):
    """Generate a random date from start_year to now"""
    start = datetime(start_year, 1, 1)
    end = datetime.now() + timedelta(days=365)  # Include future dates
    return start + timedelta(days=random.randint(0, (end - start).days))

def random_string(length=10):
    """Generate a random string of fixed length"""
    letters = string.ascii_letters + string.digits
    return ''.join(random.choice(letters) for _ in range(length))

def random_goal():
    """Generate a random campaign goal"""
    goals = [
        "Increase brand awareness",
        "Drive customer acquisition",
        "Boost customer retention",
        "Launch new product",
        "Seasonal promotion",
        "Customer reactivation",
        "Market expansion",
        "Cross-selling campaign",
        "Brand loyalty program",
        "Customer feedback initiative"
    ]
    return random.choice(goals)

def random_audience():
    """Generate a random target audience criteria"""
    demographics = ["Young professionals", "Families", "Seniors", "Students", "Business owners"]
    behaviors = ["Recent purchasers", "High-value customers", "Inactive customers", "Frequent shoppers"]
    locations = ["Urban areas", "Suburban regions", "Rural communities", "Metropolitan zones"]
    
    return f"{random.choice(demographics)} in {random.choice(locations)} who are {random.choice(behaviors)}"

def generate_sample_data(num_campaigns=100):
    campaigns = []
    
    for i in range(num_campaigns):
        # Generate random dates
        start_date = random_date()
        end_date = start_date + timedelta(days=random.randint(30, 180))
        
        # Generate random costs with more variation
        base_cost_multiplier = random.uniform(0.5, 2.0)
        printing_cost = random.uniform(500, 10000) * base_cost_multiplier
        postage_cost = random.uniform(1000, 15000) * base_cost_multiplier
        data_cost = random.uniform(300, 5000) * base_cost_multiplier
        other_costs = random.uniform(100, 3000) * base_cost_multiplier
        total_cost_actual = printing_cost + postage_cost + data_cost + other_costs
        total_cost_planned = total_cost_actual * random.uniform(0.8, 1.4)
        
        # Generate random performance metrics with more realistic variations
        total_pieces = random.randint(1000, 50000)
        response_rate = random.uniform(0.01, 0.15)  # 1% to 15%
        conversion_rate = random.uniform(0.05, 0.40)  # 5% to 40%
        total_responses = int(total_pieces * response_rate)
        total_conversions = int(total_responses * conversion_rate)
        avg_conversion_value = random.uniform(50, 1000)
        total_conversion_value = total_conversions * avg_conversion_value
        
        # Status based on dates
        current_date = datetime.now()
        if start_date > current_date:
            status = 'Planned'
        elif end_date < current_date:
            status = 'Completed'
        else:
            status = 'Active'

        campaign = {
            'campaign_id': f"CAM{str(i+1).zfill(4)}",
            'campaign_name': f"{random.choice(['Spring', 'Summer', 'Fall', 'Winter', 'Holiday', 'Special'])} Campaign {start_date.strftime('%Y-%m')}",
            'description': f"Marketing initiative focused on {random_goal().lower()} through targeted messaging and offers",
            'campaign_goal': random_goal(),
            'target_audience_criteria': random_audience(),
            'overall_start_date': start_date.strftime('%Y-%m-%d'),
            'overall_end_date': end_date.strftime('%Y-%m-%d'),
            'campaign_status': status,
            
            # Cost Details with more variation
            'overall_budget': total_cost_planned * random.uniform(1.05, 1.25),
            'total_campaign_cost_planned': total_cost_planned,
            'total_campaign_cost_actual': total_cost_actual,
            'cost_per_piece_planned': total_cost_planned / total_pieces,
            'cost_per_piece_actual': total_cost_actual / total_pieces,
            
            # Cost Breakdown
            'printing_cost_actual': printing_cost,
            'postage_cost_actual': postage_cost,
            'data_cost_actual': data_cost,
            'other_costs_actual': other_costs,
            
            # Strategy Cell with more variation
            'cell_no': f"{random.choice(string.ascii_uppercase)}{random.randint(1,9)}",
            'cell_description': f"Target segment {random.choice(['Premium', 'Standard', 'Value', 'Test', 'Control'])}",
            'assigned_creative_id': f"CR{random_string(6)}",
            'assigned_offer_code': f"OFF{random_string(4)}",
            'campaign_total_mailed': total_pieces,
            'campaign_total_responses': total_responses,
            'campaign_total_conversions': total_conversions,
            'campaign_total_conversion_value': total_conversion_value,
            
            # Weekly Mail Drop
            'mail_drop_id': f"MD{random_string(8)}",
            'mailing_week_start_date': start_date.strftime('%Y-%m-%d'),
            'planned_send_date': (start_date + timedelta(days=random.randint(1,7))).strftime('%Y-%m-%d'),
            'actual_send_date': (start_date + timedelta(days=random.randint(1,10))).strftime('%Y-%m-%d'),
            'total_pieces_sent_this_week': total_pieces,
            
            # Performance Summary with realistic metrics
            'total_campaign_pieces_sent': total_pieces,
            'total_campaign_responses': total_responses,
            'overall_response_rate': round(response_rate * 100, 2),  # Convert to percentage
            'total_campaign_conversions': total_conversions,
            'overall_conversion_rate': round(conversion_rate * 100, 2),  # Convert to percentage
            'total_campaign_conversion_value': round(total_conversion_value, 2),
            'average_conversion_value': round(avg_conversion_value, 2),
            'campaign_roi': round(((total_conversion_value - total_cost_actual) / total_cost_actual) * 100, 2)
        }
        
        campaigns.append(campaign)
    
    return campaigns

def main():
    # Generate sample data
    print("Generating 100 sample campaign records...")
    campaigns = generate_sample_data(num_campaigns=100)
    
    # Convert to DataFrame
    df = pd.DataFrame(campaigns)
    
    # Save to CSV
    output_file = 'campaign_data.csv'
    df.to_csv(output_file, index=False)
    print(f"\nGenerated sample data and saved to {output_file}")
    
    # Display summary statistics
    print("\nData Summary:")
    print(f"Total campaigns: {len(df)}")
    print(f"Date range: {df['overall_start_date'].min()} to {df['overall_end_date'].max()}")
    print("\nCampaign Status Distribution:")
    print(df['campaign_status'].value_counts())
    print("\nAverage Metrics:")
    print(f"Average Response Rate: {df['overall_response_rate'].mean():.2f}%")
    print(f"Average Conversion Rate: {df['overall_conversion_rate'].mean():.2f}%")
    print(f"Average ROI: {df['campaign_roi'].mean():.2f}%")
    
    # Display preview
    print("\nPreview of generated data:")
    preview_columns = ['campaign_id', 'campaign_name', 'campaign_status', 
                      'total_campaign_pieces_sent', 'overall_response_rate', 'campaign_roi']
    print(df[preview_columns].head())

if __name__ == "__main__":
    main() 