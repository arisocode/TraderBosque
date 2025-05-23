
export interface Quote {
  symbol: string;
  quote: {
    c: number;
    h: number;
    l: number;
    o: number;
    pc: number;
  };
}

interface SymbolsProps {
  data: Quote[];
  onSelectSymbol: (symbol: string) => void;
}

export const Symbols = ({ data, onSelectSymbol }: SymbolsProps) => {
  return (
    <table className="min-w-full text-white">
      <thead>
        <tr className="text-green-400 text-sm uppercase tracking-wider">
          <th className="px-4 py-3">Símbolo</th>
          <th className="px-4 py-3">Actual</th>
          <th className="px-4 py-3">Máximo</th>
          <th className="px-4 py-3">Mínimo</th>
          <th className="px-4 py-3">Apertura</th>
          <th className="px-4 py-3">Cierre Ant.</th>
          <th className="px-4 py-3">Gráfico</th>
        </tr>
      </thead>
      <tbody>
        {data.map((item, index) => (
          <tr key={index} className="hover:bg-gray-700 border-b border-gray-700 text-sm font-mono">
            <td className="px-4 py-2 font-bold text-green-400">{item.symbol}</td>
            <td className="px-4 py-2">${item.quote.c.toFixed(2)}</td>
            <td className="px-4 py-2">${item.quote.h.toFixed(2)}</td>
            <td className="px-4 py-2">${item.quote.l.toFixed(2)}</td>
            <td className="px-4 py-2">${item.quote.o.toFixed(2)}</td>
            <td className="px-4 py-2">${item.quote.pc.toFixed(2)}</td>
            <td className="px-4 py-2">
              <button
                onClick={() => onSelectSymbol(item.symbol)}
                className="bg-blue-600 hover:bg-blue-700 transition px-3 py-1 rounded text-white"
              >
                Ver
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};
