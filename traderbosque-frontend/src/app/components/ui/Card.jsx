export function Card({ children, ...props }) {
  return (
    <div
      {...props}
      className="bg-white shadow-md rounded-md p-4 border border-gray-200"
    >
      {children}
    </div>
  );
}

export function CardContent({ children, ...props }) {
  return (
    <div {...props} className="mt-2">
      {children}
    </div>
  );
}