import { useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import { resetError } from '../features/error/loginErrorSlice';
import { loginUser } from '../features/user/userSlice';

const Login = () => {
  const { register, handleSubmit, watch } = useForm();
  const watcher = watch();
  const dispatch = useDispatch();
  const error = useSelector((store) => store.loginError);

  useEffect(() => {
    console.log(error);
  }, [error]);

  const login = () => {
    dispatch(loginUser({ email: watcher.email, password: watcher.password }));
  };
  //TODO: Add animate-spin and deactivate button on login attempt
  return (
    <div>
      <div
        className={`shadow-xl w-96 my-4 rounded-xl transition ease-in duration-200 
         ${
           error.isActive ? 'bg-error-background/50' : 'bg-second-background/75'
         }`}
      >
        <form
          className='flex flex-col items-center mx-16'
          onSubmit={handleSubmit(login)}
        >
          <input
            {...register('email', {
              required: "Field can't be empty.",
              pattern: {
                value: /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/,
                message: 'Not a valid email',
              },
            })}
            className='
            bg-first-background text-center mt-6 border-2 rounded-md text-neutral-100
            border-first-border w-64 focus:outline-none focus:border-second-border'
            type='email'
            name='email'
            autoComplete='off'
            placeholder='Email'
          />
          <input
            {...register('password', {
              required: true,
            })}
            className='
            bg-first-background text-center mt-3 border-2 rounded-md text-neutral-100
            border-first-border w-64 focus:outline-none focus:border-second-border'
            type='password'
            name='password'
            placeholder='Password'
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
      <div className='text-neutral-100 ml-4'>
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
