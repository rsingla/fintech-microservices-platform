import streamlit as st
from src.views.navigation import render_navigation
from src.views import render_upload_section, render_data_view

def main():
    st.set_page_config(
        page_title="Campaign Manager",
        page_icon="📊",
        layout="wide"
    )

    # Add title with custom styling
    st.markdown(
        """
        <h1 style='text-align: center; padding: 1rem 0;'>
            Campaign Manager Dashboard
        </h1>
        """,
        unsafe_allow_html=True
    )

    # Render navigation and get selected page
    selected_page = render_navigation()

    # Render the selected page
    if selected_page == "Upload":
        render_upload_page()
    else:
        render_view_page()

def render_upload_page():
    """Render the upload page"""
    st.markdown("---")
    render_upload_section()

def render_view_page():
    """Render the view page"""
    st.markdown("---")
    render_data_view()

if __name__ == "__main__":
    main()