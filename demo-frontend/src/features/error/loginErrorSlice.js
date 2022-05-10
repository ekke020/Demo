import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  isActive: false,
  message: '',
};

export const errorToggle = (message) => {
  return function (dispatch) {
    dispatch(setError(message));
    setTimeout(() => {
      dispatch(resetError());
    }, 500);
  };
};

const loginErrorSlice = createSlice({
  name: 'error',
  initialState,
  reducers: {
    setError: (state, { payload }) => {
      return { isActive: true, message: payload };
    },
    resetError: (state) => {
      return initialState;
    },
  },
});

export const { setError, resetError } = loginErrorSlice.actions;
export default loginErrorSlice.reducer;
