import logo from './assets/logo-text.svg';
import './App.css';
import './AppBar.css';

function AppBar() {
    return (
        <nav className="navbar">
            <div>
                <img src={logo} alt="Logo" className="logo-img"/>
            </div>
            <div className="navbar-menu">
                <a href="#" className="navbar-menu-item">Products</a>
                <a href="#" className="navbar-menu-item">Pricing</a>
                <a href="#" className="navbar-menu-item">Organization</a>
                <a href="#" className="navbar-menu-item">Blog</a>
            </div>
            <button className="gradient-button">
                <div className="spinner-container"><div className="spinner"></div></div>
                <div className="front-layer"></div>
                <div className="button-text">Sign In</div>
            </button>
        </nav>
    );
}

export default AppBar;