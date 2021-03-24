import React from 'react'
import { useLocation } from 'react-router-dom'

export const NoMatch = () => {
    let location = useLocation();
  
    return (
      <div>
        <h1> 404 Not Found </h1>
        <p>Not match for <code>{location.pathname}</code></p>
      </div>
    );
}
