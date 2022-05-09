import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import { fingerPrint, home, login } from '../icons/icons';

const Navbar = () => {
  const { isLoggedIn } = useSelector((store) => store.user);

  return (
    <nav className='bg-second-background h-10 flex justify-between'>
      <div className='ml-5 flex'>
        <button onClick={() => <Link to='/home' />}>{home()}</button>
      </div>
      <div className='mr-5 flex'>
        <button
          onClick={() => {
            <Link to='/account/login' />;
          }}
        >
          {login()}
        </button>
      </div>
    </nav>
  );
};

export default Navbar;
