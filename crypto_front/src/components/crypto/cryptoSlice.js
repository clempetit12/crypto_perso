import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080/crypto/all"; 
const API_URL_PERFORMANCE = "http://localhost:8080/crypto/performance"; 

export const getAllCryptos = createAsyncThunk(
  "cryptos/getAllCryptos",
  async () => {
      return (await axios.get(`${API_URL}`)).data;
  }
);

export const getOnecrypto = createAsyncThunk(
  "cryptos/getOnecrypto",
  async (id) => {
      return (await axios.get(`${API_URL}/crypto/${id}`)).data;
  }
);

export const getPerformanceByDate = createAsyncThunk(
  "performance/getPerformanceByDate",
  async (date) => {
    return (await axios.get(`${API_URL_PERFORMANCE}/${date}`)).data;
  }
);



const cryptoSlice = createSlice({
  name: "cryptos",
  initialState: {
    cryptos: [],
    selectedCrypto: null,
    selectedPerformanceData : null
  },
  reducers: {
    setSelectedCrypto: (state, action) => {
      state.selectedCrypto = action.payload;
    },
    updateCryptos: (state, action) => {
      state.cryptos = action.payload;
    },
    setPerformanceData : (state,action) => {
      state.selectedPerformanceData = action.payload;
    }
  },

  extraReducers: (builder) => {
    builder.addCase(getAllCryptos.fulfilled, (state, action) => {
      console.log("Fulfilled with data:", action.payload);
      state.cryptos = action.payload;
    });    
  
    builder.addCase(getAllCryptos.pending, (state) => {
      console.log("Pending...");
      state.cryptos = [];
    });
  
    builder.addCase(getAllCryptos.rejected, (state, action) => {
      console.error("Rejected with error:", action.error.message);
      state.cryptos = [];
    });
    builder.addCase(getPerformanceByDate.fulfilled, (state, action) => {
      console.log("Performance data fetched:", action.payload);
      state.selectedPerformanceData = action.payload;
    });

    builder.addCase(getPerformanceByDate.pending, (state) => {
      console.log("Fetching performance data...");
    });
  
    builder.addCase(getPerformanceByDate.rejected, (state, action) => {
      console.error("Failed to fetch performance data:", action.error.message);
    });
    
  }
  
});

export const { setSelectedCrypto,selectedCrypto, setSelectedPerformanceData, selectedPerformanceData } = cryptoSlice.actions;

export default cryptoSlice.reducer;
