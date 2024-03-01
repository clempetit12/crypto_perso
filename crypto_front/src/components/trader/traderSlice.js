import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "";

export const signIn = createAsyncThunk(
  "auth/signIn",
  async (trader) => {
    const response = await axios.post(`${API_URL}/traders/signin`, {
      email: trader.email,
      password: trader.password
    });
    return response.data;
  }
);

export const signUp = createAsyncThunk(
  "auth/signUp",
  async (trader) => {
    const response = await axios.post(`${API_URL}/traders/signup`, {
      email: trader.email,
      password: trader.password
    });
    return response.data;
  }
);


const traderSlice = createSlice({
  name: "auth",
  initialState: {
    traderMode: "Sign in",
    trader: null
  },
  reducers: {
    logOutAction(state, action) {
      state.trader = null;
      localStorage.removeItem('token');
    },
    setTraderMode: (state, action) => {
        state.traderMode = action.payload;
    }
  },
  extraReducers: (builder) => {
    builder.addCase(signIn.fulfilled, (state, action) => {
      state.trader = action.payload;
      console.log(state.trader);
      localStorage.setItem('token', action.payload.idToken);
    });

    builder.addCase(signUp.fulfilled, (state, action) => {
      state.trader = action.payload;
      console.log(state.trader);
      localStorage.setItem('token', action.payload.idToken);
    });
  }
});

export const { logOutAction, setTraderMode } = traderSlice.actions;

export default traderSlice.reducer;
