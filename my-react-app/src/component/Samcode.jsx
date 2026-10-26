import React from 'react'
import { useState } from 'react';

function Samcode() {
const [pointer,setPointer] = useState(0);

function ChangePointer(){
  setPointer(pointer+1);
}


  return (
    <div>
      <button onClick={ChangePointer}>Pointer: {pointer}</button>
    </div>
  )
}

export default Samcode