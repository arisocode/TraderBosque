'use client';

import axios from "axios";
import { CreditCard, Loader2 } from "lucide-react";
import { FormEvent, useState } from 'react';
import { toast } from "sonner";
import { Button } from "../../components/ui/button";
import { Input } from "../../components/ui/input";
import { Label } from "../../components/ui/label";

export default function Page() {
  const [loading, setLoading] = useState(false)
  const [form, setForm] = useState({
    amount: '',
    cardNumber: '',
    name: '',
    expiry: '',
    cvv: '',
    accountOwnerName: '',
    bankAccountType: '',
    bankAccountNumber: '',
    bankRoutingNumber: '',
    nickname: ''
  })
  const [achId, setAchId] = useState('')
  const [requiresACH, setRequiresACH] = useState(false);
  const [accountId, setAccountId] = useState('')

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setLoading(true);

    try {
      const accountRes = await axios.get('http://localhost:8080/api/usuario/v1/accountId', { withCredentials: true });
      const id = accountRes.data.accountId;
      setAccountId(id);

      let achIdFetched = "";

      try {
        const achRes = await axios.get(`http://localhost:8080/v1/api/funding/ach/${id}`);
        
        if (achRes.data?.id) {
          achIdFetched = achRes.data.id;
        } else {
          const achData = {
            account_owner_name: form.accountOwnerName,
            bank_account_type: form.bankAccountType,
            bank_account_number: form.bankAccountNumber,
            bank_routing_number: form.bankRoutingNumber,
            nickname: form.nickname
          };

          const createdAch = await axios.post(`http://localhost:8080/v1/api/funding/ach/${id}`, achData);
          achIdFetched = createdAch.data.id;
        }

      } catch (error) {
        console.error("Error al verificar o crear ACH:", error);
      }

      setAchId(achIdFetched);

      const transferData = {
        transfer_type: "ach",
        relationship_id: achIdFetched,
        amount: form.amount,
        direction: "INCOMING"
      };

      await axios.post(`http://localhost:8080/v1/api/funding/transfer/${id}`, transferData);
      toast.success(`Se han recargado $${form.amount} a tu saldo.`);
    } catch (err) {
      console.error(err);
      toast.error("Error al procesar la recarga");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-white to-zinc-100 p-10">
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-10 max-w-7xl mx-auto">
        <div className="flex flex-col justify-center">
          <h1 className="text-4xl font-bold text-zinc-800 mb-4">Recarga tu saldo</h1>
          <p className="text-zinc-600 mb-6">
            Añade fondos a tu cuenta de manera segura usando una tarjeta o cuenta bancaria.
          </p>
          <div className="rounded-xl border border-zinc-200 p-6 bg-white shadow-md">
            <form onSubmit={handleSubmit} className="space-y-5">
              {!achId && (
                <div className="space-y-4">
                  <h2 className="text-lg font-semibold text-zinc-700">Información Bancaria</h2>
                  <div>
                    <Label htmlFor="accountOwnerName">Titular de la Cuenta</Label>
                    <Input
                      name="accountOwnerName"
                      value={form.accountOwnerName}
                      onChange={handleChange}
                      // removed required
                    />
                  </div>
                  <div>
                    <Label htmlFor="bankAccountType">Tipo de Cuenta</Label>
                    <select
                      name="bankAccountType"
                      value={form.bankAccountType}
                      onChange={handleChange}
                      className="w-full p-2 border rounded"
                      // removed required
                    >
                      <option value="">-- Selecciona el tipo de cuenta --</option>
                      <option value="CHECKING">Corriente</option>
                      <option value="SAVINGS">Ahorros</option>
                    </select>
                  </div>
                  <div>
                    <Label htmlFor="bankAccountNumber">Número de Cuenta</Label>
                    <Input
                      name="bankAccountNumber"
                      value={form.bankAccountNumber}
                      onChange={handleChange}
                      // removed required
                    />
                  </div>
                  <div>
                    <Label htmlFor="bankRoutingNumber">Routing Number</Label>
                    <Input
                      name="bankRoutingNumber"
                      value={form.bankRoutingNumber}
                      onChange={handleChange}
                      // removed required
                    />
                  </div>
                  <div>
                    <Label htmlFor="nickname">Apodo</Label>
                    <Input
                      name="nickname"
                      value={form.nickname}
                      onChange={handleChange}
                      // removed required
                    />
                  </div>
                </div>
              )}

              <h2 className="text-lg font-semibold text-zinc-700 mt-6">Datos de la Tarjeta</h2>
              <div>
                <Label htmlFor="amount">Monto</Label>
                <Input name="amount" type="number" value={form.amount} onChange={handleChange} min={100} required />
              </div>
              <div>
                <Label htmlFor="cardNumber">Número de Tarjeta</Label>
                <Input name="cardNumber" value={form.cardNumber} onChange={handleChange} maxLength={19} required />
              </div>
              <div>
                <Label htmlFor="name">Nombre del Titular</Label>
                <Input name="name" value={form.name} onChange={handleChange} required />
              </div>
              <div className="flex gap-4">
                <div className="flex-1">
                  <Label htmlFor="expiry">Vencimiento</Label>
                  <Input name="expiry" value={form.expiry} onChange={handleChange} maxLength={5} required />
                </div>
                <div className="flex-1">
                  <Label htmlFor="cvv">CVV</Label>
                  <Input name="cvv" value={form.cvv} onChange={handleChange} maxLength={4} required />
                </div>
              </div>

              <Button
                type="submit"
                className="w-full bg-indigo-600 hover:bg-indigo-700 text-white text-lg rounded-lg"
                disabled={loading}
              >
                {loading ? (
                  <span className="flex items-center justify-center">
                    <Loader2 className="animate-spin w-5 h-5 mr-2" />
                    Procesando...
                  </span>
                ) : (
                  `Recargar $${form.amount || ''}`
                )}
              </Button>
            </form>
          </div>
        </div>

        <div className="hidden lg:flex items-center justify-center">
          <div className="w-full max-w-md p-8 bg-indigo-50 border border-indigo-200 rounded-2xl shadow-inner">
            <div className="text-indigo-700 text-center">
              <CreditCard className="w-12 h-12 mx-auto mb-4" />
              <h2 className="text-2xl font-bold">Transacciones Seguras</h2>
              <p className="mt-2 text-sm text-indigo-600">
                Usamos encriptación de nivel bancario para proteger tu información financiera.
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
