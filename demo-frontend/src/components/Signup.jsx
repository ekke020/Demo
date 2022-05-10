import { useForm } from 'react-hook-form';
import axios from 'axios';
import { useEffect, useState } from 'react';

const Signup = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    watch,
  } = useForm();
  const watcher = watch();
  const [error, setError] = useState('');
  //TODO: Figure out what to do with error messages
  useEffect(() => {
    if (!error) {
      const timeId = setTimeout(() => {
        setError('');
      }, 5000);
      return () => {
        clearTimeout(timeId);
      };
    }
  }, [error, setError]);

  const submit = async (event) => {
    try {
      const response = await axios.post('http://localhost:8080/user/add', {
        email: watcher.email,
        password: watcher.password,
        name: watcher.name,
      });
      console.log(response.data);
    } catch (error) {
      setError(error.response.data.message);
    }
  };

  return (
    <div className='shadow-xl w-96 my-4 rounded-xl bg-second-background/75'>
      <form
        className='flex flex-col items-center mx-16'
        onSubmit={handleSubmit(submit)}
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
        bg-first-background text-center mt-6 border-2 rounded-md
        border-first-foreground w-64 focus:outline-none focus:border-zinc-500'
          placeholder='Email'
          autoComplete='off'
          name='email'
        />
        <p className='text-xs h-4 text-error-text'>
          {errors.email && errors.email.message}
        </p>
        <input
          {...register('name', { required: "Field can't be empty." })}
          className='
        bg-first-background text-center border-2 rounded-md 
        border-first-foreground w-64 focus:outline-none focus:border-zinc-500'
          placeholder='Username'
          name='name'
        />
        <p className='text-xs h-4 text-error-text'>
          {errors.name && errors.name.message}
        </p>
        <input
          {...register('password', { required: "Field can't be empty." })}
          className='
        bg-first-background text-center border-2 rounded-md 
        border-first-foreground w-64 focus:outline-none focus:border-zinc-500'
          placeholder='Password'
          name='password'
          type='password'
        />
        <p className='text-xs h-4 text-error-text'>
          {errors.password && errors.password.message}
        </p>
        <input
          {...register('confirmPassword', {
            required: true,
            validate: (value) =>
              value === watcher.password || 'Password does not match',
          })}
          className='
        bg-first-background text-center border-2 rounded-md 
        border-first-foreground w-64 focus:outline-none focus:border-zinc-500'
          placeholder='Confirm password'
          name='confirmPassword'
          type='password'
        />
        <p className='text-xs h-4 text-error-text'>
          {errors.confirmPassword && errors.confirmPassword.message}
        </p>
        <input
          type='submit'
          value='Create account'
          className='
            rounded shadow bg-second-background mt-4
            text-neutral-100 w-40 mb-6 hover:cursor-pointer'
        />
      </form>
    </div>
  );
};

export default Signup;
