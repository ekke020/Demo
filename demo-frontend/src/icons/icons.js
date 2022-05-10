import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

export const FingerPrint = () => {
  const navigate = useNavigate();
  return (
    <svg
      xmlns='http://www.w3.org/2000/svg'
      className='h-8 w-8'
      fill='none'
      onClick={() => navigate('/')}
      viewBox='0 0 24 24'
      stroke='currentColor'
      strokeWidth={2}
    >
      <path
        strokeLinecap='round'
        strokeLinejoin='round'
        d='M12 11c0 3.517-1.009 6.799-2.753 9.571m-3.44-2.04l.054-.09A13.916 13.916 0 008 11a4 4 0 118 0c0 1.017-.07 2.019-.203 3m-2.118 6.844A21.88 21.88 0 0015.171 17m3.839 1.132c.645-2.266.99-4.659.99-7.132A8 8 0 008 4.07M3 15.364c.64-1.319 1-2.8 1-4.364 0-1.457.39-2.823 1.07-4'
      />
    </svg>
  );
};

export const Home = () => {
  const navigate = useNavigate();
  return (
    <svg
      xmlns='http://www.w3.org/2000/svg'
      className='
        h-10 w-10 hover:stroke-nav-icon-hover/40 
        transition-colors duration-500 ease-linear'
      fill='none'
      onClick={() => navigate('/')}
      viewBox='0 0 24 24'
      stroke='currentColor'
      strokeWidth={2}
    >
      <path
        strokeLinecap='round'
        strokeLinejoin='round'
        d='M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6'
      />
    </svg>
  );
};

export const Login = () => {
  const navigate = useNavigate();
  return (
    <svg
      xmlns='http://www.w3.org/2000/svg'
      className='
        h-10 w-10 hover:stroke-nav-icon-hover/40 
        transition-colors duration-500 ease-linear'
      fill='none'
      onClick={() => navigate('/login')}
      viewBox='0 0 24 24'
      stroke='currentColor'
      strokeWidth={2}
    >
      <path
        strokeLinecap='round'
        strokeLinejoin='round'
        d='M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h7a3 3 0 013 3v1'
      />
    </svg>
  );
};
