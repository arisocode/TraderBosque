export function Label({ children, htmlFor, ...props }) {
  return (
    <label
      {...props}
      htmlFor={htmlFor}
      className="block text-sm font-medium text-gray-700 mb-1"
    >
      {children}
    </label>
  );
}