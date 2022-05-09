import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { useSelector } from 'react-redux';

import Login from './components/Login';
import Signup from './components/Signup';
import NotFound from './components/NotFound';
import Home from './components/Home';
import Navbar from './components/Navbar';

const App = () => {
  const { isLoggedIn } = useSelector((store) => store.user);

  return (
    <Router>
      <Navbar />
      <div className='flex justify-center'>
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='*' element={<NotFound />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;

/* <Route index element={<Home />} />
<Route path='home' element={<Home />} />
<Route path='account'>
<Route path='login' element={<Login />} />
<Route path='signup' element={<Signup />} />
</Route>
<Route path='*' element={<NotFound />} /> */
