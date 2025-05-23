import axios from "axios";
import { useEffect, useState } from "react";

export type Holding = {
    symbol: string;
    quantity: number;
    avgPrice: number;
    currentPrice: number;
    totalInvested: number;
    currentValue: number;
    gainLossAmount: number;
    gainLossPercentage: number;
    };

    export type Portfolio = {
    holdings: Holding[];
    totalInvested: number;
    totalCurrentValue: number;
    totalGainLossAmount: number;
    totalGainLossPercentage: number;
    };

    export function usePortfolio() {
    const [portfolio, setPortfolio] = useState<Portfolio | null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        axios
        .get("http://localhost:8080/v1/api/portfolio/", { withCredentials: true })
        .then((res) => setPortfolio(res.data))
        .catch(console.error)
        .finally(() => setLoading(false));
    }, []);

    return { portfolio, loading };
}
