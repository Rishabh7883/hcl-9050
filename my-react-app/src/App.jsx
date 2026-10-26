import { useEffect, useState } from 'react';
import './App.css';

function App() {
  const [counter, setCounter] = useState(0);

    useEffect(()=>{
      console.log('componeent is render');
      console.log("counter"+counter);
    })
    
  function Changecounter(){
    setCounter(counter+2); 
  }

  return (
    <>
      <h2>Welcome to JSX</h2>

      <div>
        {/* <Samcode empdata={employee[2]} /> */}
        {/* {employee.map((ele) => (
          <Samcode key={ele.empid} empdata={ele} />
        ))}

        <button onClick={() => setCount(count + 1)}>
          Count: {count}
        </button> */}
        <h2>Counter value</h2>
        <button onClick={Changecounter}>Counter: {counter}</button>
      
      </div>
    </>
  );
}

export default App;