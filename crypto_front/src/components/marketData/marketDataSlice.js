import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080/marketData"; 

export const getAllMarketDatas = createAsyncThunk(
  "marketDatas/getAll",
  async () => {
      return (await axios.get(`${API_URL}/all`)).data;
  }
);

export const getMarketDataForOneCrypto = createAsyncThunk(
  "marketDatas/getOnecrypto",
  async (id) => {
      return (await axios.get(`${API_URL}/${id}`)).data;
  }
);


const marketDataSlice = createSlice({
  name: "marketDatas",
  initialState: {
    marketDatas: [],
    selectedData: null
  },
  reducers: {
    setSelectedData: (state, action) => {
      state.selectedData = action.payload;
    },
    updateData: (state, action) => {
      state.marketDatas = action.payload;
    },
  },

  extraReducers: (builder) => {
    builder.addCase(getAllMarketDatas.fulfilled, (state, action) => {
      console.log("Fulfilled with data:", action.payload);
      state.marketDatas = action.payload;
    });    
  
    builder.addCase(getAllMarketDatas.pending, (state) => {
      console.log("Pending...");
      state.marketDatas = [];
    });
  
    builder.addCase(getAllMarketDatas.rejected, (state, action) => {
      console.error("Rejected with error:", action.error.message);
      state.marketDatas = [];
    });
    
  }
  
});

export const { setSelectedData } = marketDataSlice.actions;

export default marketDataSlice.reducer;
