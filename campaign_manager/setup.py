from setuptools import setup, find_packages

setup(
    name="campaign_manager",
    version="0.1",
    packages=find_packages(),
    install_requires=[
        'streamlit',
        'pandas',
        'pymongo',
        'python-dotenv',
    ],
) 