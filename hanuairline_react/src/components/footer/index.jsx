import React from 'react'
import './style.scss'
import CONFIG from '../../config'

const currentYear = new Date().getFullYear()

const Footer = () => {
  return (
    <footer className="global-footer">
      <div>
        Copyright &copy; {currentYear} {CONFIG.title} -
        <a href="https://github.com/Vu1911/hanuairline-webapp" target="_blank" rel="noopener noreferrer">Hanu Airline</a>
      </div>
    </footer>
  )
}

export default Footer
