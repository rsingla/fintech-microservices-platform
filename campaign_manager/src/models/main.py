import streamlit as st
import pymongo
import pandas as pd
import os

# Database setup (MongoDB)
try:
    client = pymongo.MongoClient("mongodb://localhost:27017/")
    db = client["mydatabase"]
    collection = db["mycollection"]
except pymongo.errors.ConnectionFailure as e:
    st.error(f"Could not connect to MongoDB: {e}")
    exit()

# UI setup with Streamlit
st.title("My Streamlit App with MongoDB")

# Check if a collection exists, and create it if not
if "mycollection" not in db.list_collection_names():
  st.write("Creating mycollection in database...")
  db.create_collection("mycollection")
  st.write("Created mycollection")

# Function to load data into MongoDB if not already loaded
def load_data_to_mongodb():
    try:
        if collection.count_documents({}) == 0:
            # Check if src/data.csv exists and create if not
            if not os.path.exists("src/data.csv"):
                with open("src/data.csv", "w") as f:
                  f.write("col1,col2\nval1,val2")
            df = pd.read_csv("src/data.csv")
            data = df.to_dict(orient="records")
            collection.insert_many(data)
            st.write("Data loaded into MongoDB.")
        else:
          st.write("Data already present in collection.")
    except Exception as e:
      st.error(f"Error: {e}")


# Button to load data
if st.button("Load Data"):
    load_data_to_mongodb()

# Show data in the collection
if collection.count_documents({}) > 0:
  data_list = list(collection.find())
  st.write("Data in MongoDB:")
  st.write(pd.DataFrame(data_list))

# Basic index page display
st.write("Welcome to my Streamlit app!")

#Main
def main():
    pass

if __name__ == "__main__":
    main()
