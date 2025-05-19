// components/WalletTopUpButton.tsx
import { loadStripe } from '@stripe/stripe-js';

const stripePromise = loadStripe(process.env.NEXT_PUBLIC_STRIPE_PUBLISHABLE_KEY!);

interface WalletTopUpButtonProps {
    amount: number; // Monto en centavos
    username: string;
}

const WalletTopUpButton: React.FC<WalletTopUpButtonProps> = ({ amount, username }) => {
    const handleTopUp = async () => {
        const response = await fetch('/api/stripe/create-wallet-session', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ amount, username }),
        });

        const data = await response.json();

        const stripe = await stripePromise;
        if (stripe) {
            await stripe.redirectToCheckout({ sessionId: data.sessionId });
        }
    };

    return <button onClick={handleTopUp}>Recargar ${amount / 100}</button>;
};

export default WalletTopUpButton;
