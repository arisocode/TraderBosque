"use client"
import { useState } from "react";
import Layout from "../../../components/accLayout";
import WalletTopUpButton from "@/app/components/wallet/page";

export default function page() {

    const [amountValue, setAmountValue] = useState<number>(0)

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setAmountValue(Number(e.target.value))
    }

    return (
        <Layout>
            <div className="flex flex-col gap-4 max-w-sm mx-auto mt-10">
                <label className="text-lg font-medium">Monto a recargar (COP):</label>
                <input
                    type="number"
                    value={amountValue}
                    onChange={handleChange}
                    className="border p-2 rounded shadow"
                    placeholder="Ej: 10000"
                    min={1000}
                    step={1000}
                />
                <WalletTopUpButton amount={amountValue} />
            </div>
        </Layout>
    );
}
