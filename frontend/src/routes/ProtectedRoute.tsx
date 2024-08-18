import {useLocation} from 'react-router-dom';
import {useAuth0} from '@auth0/auth0-react';
import storage from "../utils/storage.ts";

const ProtectedRoute = ({children}) => {
    const {isAuthenticated, loginWithRedirect, getAccessTokenSilently, isLoading} = useAuth0();
    const location = useLocation();

    if (isLoading) {
        return null;
    }

    if (!isAuthenticated) {
        console.log('ProtectedRoute: user is not authenticated, redirecting to login page...');
        loginWithRedirect({appState: {returnTo: location.pathname}});

        return null;
    }

    // Get the access token
    const getAccessToken = async () => {
        let token;
        try {
            token = await getAccessTokenSilently();
            storage.set('token', token);
        } catch (e) {
            console.error(e);
        }
        return token;
    };

    // Call the function to get the access token
    getAccessToken();

    return children;
};

export default ProtectedRoute;