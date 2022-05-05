import axios from 'axios';
import { useState } from 'react';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const login = async (event) => {
    event.preventDefault();
    const response = await axios.post('http://localhost:8080/user/login', {
      email: email,
      password: password,
    });
    console.log(response);
  };

  return (
    <div className='shadow-xl w-96 mt-4 rounded-xl bg-warm-orange/75'>
      <form className='flex flex-col items-center' onSubmit={login}>
        <input
          className='
            bg-warm-white text-center mt-6 border-2 rounded-md
            focus:invalid:border-pink-500 focus:invalid:ring-pink-500
            invalid:border-pink-500 invalid:text-pink-600
            border-warm-pink w-64 focus:outline-none focus:border-zinc-500'
          type='email'
          name='email'
          autoComplete='off'
          placeholder='Email'
          onChange={({ target }) => setEmail(target.value)}
        />
        <input
          className='
            bg-warm-white text-center mb-6 mt-3 border-2 rounded-md 
            border-warm-pink w-64 focus:outline-none focus:border-zinc-500'
          type='password'
          name='password'
          placeholder='Password'
          onChange={({ target }) => setPassword(target.value)}
        />
        <input
          type='submit'
          value='Login'
          className='rounded shadow border-warm-pink bg-warm-orange text-neutral-100 w-40 mb-6'
        />
      </form>
    </div>
  );
};

export default Login;
