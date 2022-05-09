import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link, useNavigate } from 'react-router-dom';
import { loginUser } from '../features/user/userSlice';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { isLoggedIn } = useSelector((store) => store.user);
  useEffect(() => {
    if (isLoggedIn) {
      navigate('/');
    }
  }, [navigate, isLoggedIn]);

  const login = (event) => {
    event.preventDefault();
    dispatch(loginUser({ email: email, password: password }));
    setPassword('');
  };

  //TODO: Add validation with react-form and extract input to a separate component
  return (
    <div>
      <div className='shadow-xl w-96 my-4 rounded-xl bg-second-background/75'>
        <form className='flex flex-col items-center mx-16' onSubmit={login}>
          <input
            className='
            bg-first-background text-center mt-6 border-2 rounded-md
            focus:invalid:border-pink-500 focus:invalid:ring-pink-500
            invalid:border-pink-500 invalid:text-pink-600
            border-first-foreground w-64 focus:outline-none focus:border-zinc-500'
            type='email'
            name='email'
            autoComplete='off'
            placeholder='Email'
            value={email}
            onChange={({ target }) => setEmail(target.value)}
          />
          <input
            className='
            bg-first-background text-center mt-3 border-2 rounded-md
            border-first-foreground w-64 focus:outline-none focus:border-zinc-500'
            type='password'
            name='password'
            placeholder='Password'
            value={password}
            onChange={({ target }) => setPassword(target.value)}
          />
          <Link
            className='text-xs ml-auto mb-4 underline text-sky-600'
            to='recovery'
          >
            Forgot password ?
          </Link>
          <input
            type='submit'
            value='Login'
            className='
            rounded shadow bg-second-background
            text-neutral-100 w-40 mb-6 hover:cursor-pointer'
          />
        </form>
      </div>
      <div className='text-dark-text ml-4'>
        Dont have an account?{' '}
        <Link
          className='text-xs ml-auto mb-2 underline text-sky-600'
          to='/account/signup'
        >
          Sign up
        </Link>
      </div>
    </div>
  );
};

export default Login;
