import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import {Auth0Provider} from "@auth0/auth0-react";

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <Auth0Provider
            domain="dev-q6qkwuzccru8mu10.us.auth0.com"
            clientId="2EnR3RZSo0akaVPIZqd1EBMZzHghgOKx"
            cacheLocation={'localstorage'}
            useRefreshTokens={true}
            authorizationParams={
                {
                    audience: 'https://api-ggstudy.ptit.id.vn',
                }
            }
             redirectUri={window.location.origin + '/callback'}>
            <App/>
        </Auth0Provider>

    </React.StrictMode>,
)
