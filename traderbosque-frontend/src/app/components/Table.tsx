import { Table, TableBody, TableCell, TableHead, TableHeadCell, TableRow } from "flowbite-react";

type props = {
    data: {
        name: string,
        email: string,
        phone: number,
        userName: string
    }
}

//Tabla conseguida de FlowBite disponible en: https://flowbite-react.com/docs/components/table, ajustada para permitir parametros y usarse de manera "rápida"
export function TableComponent({ data }: props) {
    return (
        <div className="overflow-x-auto">
            <Table>
                <TableHead>
                    <TableRow>
                        <TableHeadCell>Nombre</TableHeadCell>
                        <TableHeadCell>Correo Electronico</TableHeadCell>
                        <TableHeadCell>Número de telefono</TableHeadCell>
                        <TableHeadCell>Nombre de ususario</TableHeadCell>
                    </TableRow>
                </TableHead>
                <TableBody className="divide-y">
                    <TableRow className="bg-white dark:border-gray-700 dark:bg-gray-800">
                        <TableCell className="whitespace-nowrap font-medium text-gray-900 dark:text-white">
                            {data.name}
                        </TableCell>
                        <TableCell>{data.email}</TableCell>
                        <TableCell>{data.phone}</TableCell>
                        <TableCell>{data.userName}</TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </div>
    );
}

type Quote = {
    c: number;
    h: number;  // Máximo
    l: number;  // Mínimo
    o: number;  // Apertura
    pc: number; // Cierre pasado
};

type SymbolData = {
    symbol: string;
    quote: Quote;
};

type Props1 = {
    data: SymbolData[];
};

export function TableSymbols({ data }: Props1) {
    return (
        <div className="overflow-x-auto">
            <Table>
                <TableHead>
                    <TableRow>
                        <TableHeadCell>Símbolo</TableHeadCell>
                        <TableHeadCell>Actual</TableHeadCell>
                        <TableHeadCell>Máximo</TableHeadCell>
                        <TableHeadCell>Mínimo</TableHeadCell>
                        <TableHeadCell>Apertura</TableHeadCell>
                        <TableHeadCell>Cierre Pasado</TableHeadCell>
                    </TableRow>
                </TableHead>
                <TableBody className="divide-y">
                    {data.map((item) => (
                        <TableRow key={item.symbol} className="bg-white dark:border-gray-700 dark:bg-gray-800">
                            <TableCell className="whitespace-nowrap font-medium text-gray-900 dark:text-white">
                                {item.symbol}
                            </TableCell>
                            <TableCell>{item.quote.c}</TableCell>
                            <TableCell>{item.quote.h}</TableCell>
                            <TableCell>{item.quote.l}</TableCell>
                            <TableCell>{item.quote.o}</TableCell>
                            <TableCell>{item.quote.pc}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </div>
    );
}