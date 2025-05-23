import { useState } from 'react';

interface WalletTopUpButtonProps {
    amount: number;
}

const WalletTopUpButton: React.FC<WalletTopUpButtonProps> = ({ amount }) => {
    const [loading, setLoading] = useState(false)
    const query = new URLSearchParams(window.location.search); //Por medio de un condicional manejar lo demas

    const handleRecharge = async () => {
        setLoading(true)

        const username = localStorage.getItem('username')

        const response = await fetch('http://localhost:8080/api/checkout/create-wallet-session', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                amount: amount,
                username: username
            }),
        })

        const data = await response.json()

        if (data && data.url) {
            window.location.href = data.url
        } else {
            alert('Error al generar el enlace de pago')
        }

        setLoading(false)
    }

    return (
        <button onClick={handleRecharge} disabled={loading}>
            {loading ? 'Redirigiendo...' : 'Recargar Saldo'}
        </button>
    );
};

export default WalletTopUpButton;
