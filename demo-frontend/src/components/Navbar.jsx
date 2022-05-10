import { useSelector } from 'react-redux';
import { FingerPrint, Home, Login } from '../icons/icons';

const Navbar = () => {
  const { isLoggedIn } = useSelector((store) => store.user);

  return (
    <nav
      className='
        flex flex-col justify-end w-full sticky 
        top-0 h-14 z-40 bg-transparent backdrop-blur-sm 
        hover:bg-first-background/60 transition-bg duration-300 ease-in-out'
    >
      <div className='pb-2 flex justify-between border-solid border-b border-second-border/20'>
        <div className='ml-5 flex'>
          <Home />
        </div>
        <div className='mr-5 flex'>
          <Login />
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
