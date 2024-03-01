import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = ""; 

export const getWallet = createAsyncThunk(
  "wallets/getWallet",
  async () => {
      return (await axios.get(`${API_URL}`)).data;
  }
);


const walletSlice = createSlice({
    name: "wallets",
    initialState: {
      wallets: [],
      selectedWallet: null
    },
    reducers: {
      setSelectedWallet: (state, action) => {
        state.selectedWallet = action.payload;
      }
    },
    extraReducers: (builder) => {
      builder.addCase(getWallet.fulfilled, (state, action) => {
        console.log("Fulfilled with data:", action.payload);
        state.wallets = action.payload;
      });
  
      builder.addCase(getWallet.rejected, (state, action) => {
        console.error("Failed to fetch wallet data:", action.error);
      });
    }
  });


export const { setSelectedWallet } = walletSlice.actions;

export default walletSlice.reducer;
