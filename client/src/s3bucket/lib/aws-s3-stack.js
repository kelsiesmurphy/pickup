import * as iam from 'aws-sdk/clients/iam';
import * as s3 from 'aws-sdk/clients/s3';
import React, { Component } from 'react';
import { Stack, RemovalPolicy } from '@aws-cdk/core';

class AwsS3Stack extends Component {
    constructor(props) {
        super(props);

        const stack = new Stack(props.stackName, props);

        const bucket = new s3.Bucket(stack, 'my-bucket', {
            removalPolicy: RemovalPolicy.DESTROY,
            cors: [
                {
                    allowedHeaders: ['*'],
                    allowedOrigins: ['*'],
                    allowedMethods: [s3.HttpMethods.POST],
                },
            ],
        });

        bucket.addToResourcePolicy(
            new iam.PolicyStatement({
                effect: iam.Effect.ALLOW,
                principals: [new iam.ServicePrincipal('lambda.amazonaws.com')],
                actions: ['s3:GetObject'],
                resources: [`${bucket.bucketArn}/*`],
            })
        );

        bucket.policy?.document.addStatements(
            new iam.PolicyStatement({
                effect: iam.Effect.ALLOW,
                principals: [new iam.ServicePrincipal('lambda.amazonaws.com')],
                actions: ['s3:GetBucketTagging'],
                resources: [bucket.bucketArn],
            })
        );
    }

    render() {
        return <div>AwsS3Stack Component</div>;
    }
}

export default AwsS3Stack;
