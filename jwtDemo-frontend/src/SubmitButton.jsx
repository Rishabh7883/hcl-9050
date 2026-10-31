import { useFormStatus } from "react-dom";

const SubmitButton = () => {
    const { pending } = useFormStatus();

    return (
        <button type="submit" disabled={pending}>
            {pending ? "Please wait..." : "Submit"}
        </button>
    );
};

export default SubmitButton;