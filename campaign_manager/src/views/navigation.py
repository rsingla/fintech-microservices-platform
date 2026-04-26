import streamlit as st

def render_navigation():
    """Render the top navigation menu"""
    st.markdown(
        """
        <style>
        .nav-container {
            display: flex;
            justify-content: center;
            padding: 1rem 0;
            background-color: #f0f2f6;
            margin-bottom: 2rem;
            border-radius: 0.5rem;
        }
        .nav-link {
            color: #0E1117;
            text-decoration: none;
            padding: 0.5rem 2rem;
            border-radius: 0.3rem;
            margin: 0 0.5rem;
        }
        .nav-link:hover {
            background-color: #dfe1e6;
        }
        .nav-link.active {
            background-color: #0E1117;
            color: white;
        }
        </style>
        """,
        unsafe_allow_html=True
    )

    menu_items = {
        "Upload": "📤 Upload Data",
        "View": "📊 View Campaigns"
    }

    col1, col2, col3 = st.columns([2,3,2])
    with col2:
        selected_page = st.radio(
            "Navigation",
            list(menu_items.keys()),
            format_func=lambda x: menu_items[x],
            horizontal=True,
            label_visibility="collapsed"
        )

    return selected_page 