import { useState } from 'react';
import { Upload } from 'react-feather';
import handleFileUpload from './api/upload_url';

const UploadImage = ({ onUpload }) => {
    const [uploaded, setUploaded] = useState(false);

    const handleUpload = async (event) => {
        const file = event.target.files[0];
        const uploadUrl = await handleFileUpload(file);
        setUploaded(true);
        console.log('uploadUrl', uploadUrl);
        onUpload(uploadUrl);
    };

    return (
        <>
            <div className="flex flex-wrap justify-between gap-8 py-4">
                <label htmlFor="email" className="text-sm font-medium text-slate-800">
                    Profile image*
                </label>
                <div className="min-w-[280px] max-w-[448px] flex flex-1 items-center justify-center">
                    <label
                        htmlFor="file"
                        className="flex h-32 w-full cursor-pointer flex-col items-center justify-center rounded-lg border border-slate-300 bg-white shadow-sm transition-colors hover:bg-slate-100"
                    >
                        <div className="mx-4 flex flex-col items-center justify-center pt-5 pb-6">
                            <Upload size={24} color="#475569" className="mb-3" />
                            <p className="mb-2 text-sm text-slate-500 ">
                                <span className="font-semibold text-green-900">
                                    Click to upload
                                </span>{" "}
                                or drag and drop a profile image.
                            </p>
                            <p className="text-xs text-slate-500 ">
                                SVG, PNG, JPG or GIF (max. 500x500px)
                            </p>
                        </div>
                        <input
                            id="file"
                            accept="image/png, image/jpeg"
                            onChange={handleUpload}
                            type="file"
                            className="hidden"
                        />
                    </label>
                </div>
            </div>

            {uploaded && <p>Uploaded successfully!</p>}
        </>
    );
};

export default UploadImage;

