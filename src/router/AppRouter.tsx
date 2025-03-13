import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';


const AppRouter: React.FC = () => {
  return (
    <div>
      <Router>
     
        <Routes>
         
          <Route paht="/product1" element={< Product1/>}></Route>
        </Routes>
    </Router>
    
    </div>
  );
};

export default AppRouter;