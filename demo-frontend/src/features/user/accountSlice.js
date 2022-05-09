import { createAsyncThunk } from '@reduxjs/toolkit';
const axios = require('axios');
const url = 'http://localhost:8080/user/';

export const createAccount = createAsyncThunk(
  'user/createUser',
  async (credentials, thunkAPI) => {
    try {
      const resp = await axios.post(`${url}add`, credentials);
      return resp.data;
    } catch (error) {
      return thunkAPI.rejectWithValue(error.response.data.message);
    }
  }
);
