import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Photo from '../pages/Photo';


const AppRouter: React.FC = () => {
  return (
    <div>
      <Router>
     
        <Routes>
         
          <Route path="/search" element={< Photo/>}></Route>
        </Routes>
    </Router>
    
    </div>
  );
};

export default AppRouter;