import { useAuth0 } from '@auth0/auth0-react';
import { useNavigate, useLocation } from 'react-router-dom';
import { useEffect } from "react";

const RedirectCallback = () => {
    const { handleRedirectCallback } = useAuth0();
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {
        const processRedirect = async () => {
            const result = await handleRedirectCallback();
            const targetUrl = result.appState && result.appState.returnTo ? result.appState.returnTo : '/';
            navigate(targetUrl);
        };
        processRedirect();
    }, [handleRedirectCallback, navigate]);

    return <div>Loading...</div>;
};

export default RedirectCallback;