def apply_custom_styles():
    return """
        <style>
        /* Main container */
        .main .block-container {
            padding-top: 2rem;
        }
        
        /* Sidebar */
        .css-1d391kg {
            padding-top: 2rem;
        }
        
        /* Metrics */
        div[data-testid="metric-container"] {
            background-color: #f0f2f6;
            border-radius: 0.5rem;
            padding: 1rem;
            text-align: center;
        }
        
        /* Tables */
        div[data-testid="stDataFrame"] > div {
            border-radius: 0.5rem;
            border: 1px solid #e0e0e0;
        }
        
        /* Buttons */
        .stButton > button {
            width: 100%;
            border-radius: 0.3rem;
        }
        
        /* Expanders */
        .streamlit-expanderHeader {
            background-color: #f0f2f6;
            border-radius: 0.3rem;
        }
        </style>
    """ 