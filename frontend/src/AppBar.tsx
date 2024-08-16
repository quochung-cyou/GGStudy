import logo from './assets/logo-text.svg';
import './App.css';
import './AppBar.css';
import { useAuth0 } from '@auth0/auth0-react';
import storage from "./utils/storage.ts";

function AppBar() {

    const {loginWithRedirect, getAccessTokenSilently, getAccessTokenWithPopup} = useAuth0();

    const handleLogin = async () => {
        try {
            await loginWithRedirect();
            const token = getAccessToken();
        } catch (e) {
            console.error(e);
        }
    };

    const getAccessToken = async () => {
        let token;
        try {
            token = await getAccessTokenSilently();
            storage.set('token', token);
        } catch (e) {
            if (e.error === 'login_required') {
                // if silent auth fails, fall back to popup
                token = await getAccessTokenWithPopup();
            } else {
                console.error(e);
            }
        }
        return token;
    };


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
            <button className="gradient-button" onClick={handleLogin}>
                <div className="spinner-container">
                    <div className="spinner"></div>
                </div>
                <div className="front-layer"></div>
                <div className="button-text">Sign In</div>
            </button>
        </nav>
    );
}

export default AppBar;