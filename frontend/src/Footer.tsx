import React from 'react';
import {motion} from 'framer-motion';
import logo from './assets/logo-text.svg';
import './Footer.css';

const Footer = () => {
    return (
        <motion.div
            className="footer"
            initial={{opacity: 0, y: 50}}
            whileInView={{opacity: 1, y: 0}}
            transition={{duration: 1}}
            viewport={{once: true}}
        >
            <div className="footer-content">
                <div className="footer-logo">
                    <img src={logo} alt="Logo" className="footer-logo"/>
                </div>

                <div className="footer-nav">
                    <a href="#" className="footer-nav-item">Home</a>
                    <a href="#demo" className="footer-nav-item">Demo</a>
                    <a href="#feature" className="footer-nav-item">Feature</a>
                    <a href="#more-information" className="footer-nav-item">More Information</a>
                </div>
            </div>
            <div className={"footer-seperator"}></div>
            <p className="footer-copyright">
                &copy; {new Date().getFullYear()} GGStudy. All rights reserved.
            </p>
        </motion.div>
    );
};

export default Footer;