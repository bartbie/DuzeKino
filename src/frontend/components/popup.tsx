import { ReactElement, ReactNode, useState } from "react";
import Popup from "reactjs-popup";

export default function CustomPopup({insides} : {insides: ReactElement}) {
  const [open, setOpen] = useState(false);
  const closeModal = () => setOpen(false);
  return (
    <div>
        <button
            type="button"
            className="button"
            onClick={() => setOpen((o) => !o)}
        > Create Movie Popup </button>
        <Popup open={open} closeOnDocumentClick onClose={closeModal}>
            <div className="modal">
                <a className="close" onClick={closeModal}> &times; </a>
                {insides}
            </div>
        </Popup>
    </div>
  );
}