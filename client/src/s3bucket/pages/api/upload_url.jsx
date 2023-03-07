import AWS from 'aws-sdk';


const handleFileUpload = async (file) => {
    console.log('handle file upload called');

    AWS.config.update({
        accessKeyId: "AKIA5ZK6F75ARSJBNA4K",
        secretAccessKey: "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",
        region: "eu-west-2",
        signatureVersion: "v4"
    })

    const s3 = new AWS.S3();
    const newFile = file.name.replace(/[^\w\s]/gi, '').replace(/\s+/g, '');

    const post = s3.createPresignedPost({
        // Bucket: process.env.BUCKET_NAME,
        Bucket: "awss3stack-mybucket15d133bf-1uuipzeff8hkx",
        Region: "eu-west-2",
        Fields: {
            key: newFile,
            'Content-Type': file.type,
        },
        Expires: 60,
        Conditions: [['content-length-range', 0, 1048576]],

    });

    const formData = new FormData();
    Object.entries(post.fields).forEach(([key, value]) => {
        formData.append(key, value);
    });
    formData.append('file', file);

    const response = await fetch(post.url, {
        method: 'POST',
        body: formData,
    });

    const imageUrl = `${response.url}/${newFile}`;
    return imageUrl;


};

export default handleFileUpload;
