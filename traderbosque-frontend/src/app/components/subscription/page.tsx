"use client"
import React, { useState, useEffect } from 'react';
import './App.css';

const Logo: React.FC = () => (
    <svg
        className='svg1'
        xmlns="http://www.w3.org/2000/svg"
        xmlnsXlink="http://www.w3.org/1999/xlink"
        width="14px"
        height="16px"
        viewBox="0 0 14 16"
        version="1.1"
    >
        <g id="Flow" stroke="none" strokeWidth="1" fill="none" fillRule="evenodd">
            <g
                id="0-Default"
                transform="translate(-121.000000, -40.000000)"
                fill="#E184DF"
            >
                <path
                    d="M127,50 L126,50 C123.238576,50 121,47.7614237 121,45 C121,42.2385763 123.238576,40 126,40 L135,40 L135,56 L133,56 L133,42 L129,42 L129,56 L127,56 L127,50 Z M127,48 L127,42 L126,42 C124.343146,42 123,43.3431458 123,45 C123,46.6568542 124.343146,48 126,48 L127,48 Z"
                    id="Pilcrow"
                />
            </g>
        </g>
    </svg>
);

const ProductDisplay: React.FC = () => {
    const handleCheckout = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/checkout/create-checkout-session", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: new URLSearchParams({ lookup_key: "Premium-73d8e1d" }),
            });

            const data = await response.json();

            if (data.url) {
                window.location.href = data.url;
            } else {
                console.error("No checkout URL in response", data);
            }
        } catch (error) {
            console.error("Error during checkout:", error);
        }
    };

    return (
        <section >
            <div className="product">
                <Logo />
                <div className="description">
                    <h3>Starter plan</h3>
                    <h5>$12.00 / month</h5>
                </div>
            </div>
            <button className="button1" id="checkout-and-portal-button" onClick={handleCheckout}>
                Checkout
            </button>
        </section>
    );
};

interface SuccessDisplayProps {
    sessionId: string;
}

const SuccessDisplay: React.FC<SuccessDisplayProps> = ({ sessionId }) => {
    const handlePortal = async () => {
        const res = await fetch("http://localhost:8080/api/checkout/create-portal-session", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: new URLSearchParams({
                session_id: sessionId
            }),
        });

        if (res.redirected) {
            window.location.href = res.url;
        } else {
            const text = await res.text();
            console.error("Error redirecting to portal:", text);
        }
    };

    return (
        <section>
            <div className="product Box-root">
                <Logo />
                <div className="description Box-root">
                    <h3>Subscription to starter plan successful!</h3>
                </div>
            </div>
            <button id="checkout-and-portal-button" onClick={handlePortal}>
                Manage your billing information
            </button>
        </section>
    );
};

interface MessageProps {
    message: string;
}

const Message: React.FC<MessageProps> = ({ message }) => (
    <section>
        <p>{message}</p>
    </section>
);

const App: React.FC = () => {
    const [message, setMessage] = useState<string>('');
    const [success, setSuccess] = useState<boolean>(false);
    const [sessionId, setSessionId] = useState<string>('');

    useEffect(() => {
        const query = new URLSearchParams(window.location.search);

        if (query.get('success')) {
            setSuccess(true);
            const session = query.get('session_id');
            if (session) setSessionId(session);
        }

        if (query.get('canceled')) {
            setSuccess(false);
            setMessage(
                "Order canceled -- continue to shop around and checkout when you're ready."
            );
        }
    }, []);

    if (!success && message === '') {
        return <ProductDisplay />;
    } else if (success && sessionId !== '') {
        return <SuccessDisplay sessionId={sessionId} />;
    } else {
        return <Message message={message} />;
    }
};

export default App;
