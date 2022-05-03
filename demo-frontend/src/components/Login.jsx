import { useState } from 'react';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const login = () => {};

  return (
    <div>
      <form className='flex flex-col' onSubmit={login}>
        <input
          className='text-center'
          type='email'
          name='email'
          placeholder='Email'
          onChange={({ target }) => setEmail(target.value)}
        />
        <input
          className='text-center'
          type='password'
          name='password'
          placeholder='Password'
          onChange={({ target }) => setPassword(target.value)}
        />
        <input
          type='submit'
          value='Login'
          className='bg-black text-neutral-100'
        />
      </form>
    </div>
  );
};

export default Login;
