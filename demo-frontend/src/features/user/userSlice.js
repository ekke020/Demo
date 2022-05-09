import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
const axios = require('axios');
const url = 'http://localhost:8080/user/';

const initialState = {
  email: '',
  name: '',
  address: '',
  country: '',
  token: '',
  isLoading: false,
  isLoggedIn: false,
};

export const loginUser = createAsyncThunk(
  'user/loginUser',
  async (credentials, thunkAPI) => {
    try {
      const resp = await axios.post(`${url}login`, credentials);
      return resp.data;
    } catch (error) {
      return thunkAPI.rejectWithValue(error.response.data.message);
    }
  }
);

const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    logoutUser: (state) => {
      state = initialState;
    },
    updateUser: (state, action) => {
      const update = action.payload;
      return { ...update, ...state };
    },
  },
  extraReducers: {
    [loginUser.pending]: (state) => {
      console.log("I'm loading");
      state.isLoading = true;
    },
    [loginUser.fulfilled]: (state, action) => {
      state.isLoading = false;
      state.isLoggedIn = true;
      state = { ...state, ...action.payload };
    },
    [loginUser.rejected]: (state, action) => {
      console.log(action.payload);
      state.isLoading = false;
    },
  },
});

export const { logoutUser, updateUser } = userSlice.actions;
export default userSlice.reducer;
