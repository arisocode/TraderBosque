// components/StockChart.tsx
'use client';

import {
    CartesianGrid,
    Line,
    LineChart,
    ResponsiveContainer,
    Tooltip,
    XAxis, YAxis
} from 'recharts';

interface Props {
  data: { time: string; price: number }[];
}

export const StockChart = ({ data }: Props) => {
  return (
    <ResponsiveContainer width="100%" height={300}>
      <LineChart data={data}>
        <CartesianGrid strokeDasharray="3 3" stroke="#444" />
        <XAxis dataKey="time" stroke="#fff" />
        <YAxis stroke="#fff" domain={['auto', 'auto']} />
        <Tooltip contentStyle={{ backgroundColor: '#111', border: 'none' }} />
        <Line type="monotone" dataKey="price" stroke="#4ade80" strokeWidth={2} dot={false} />
      </LineChart>
    </ResponsiveContainer>
  );
};
