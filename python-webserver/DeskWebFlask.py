from flask import Flask, Response, redirect
import time
from confluent_kafka import Consumer, KafkaError

#The provided code is a Flask web application that creates a video streaming service using Apache Kafka.
app = Flask(__name__)


#setup kafka
def create_kafka_consumer():
    consumer_conf = {
        'bootstrap.servers': 'localhost:9092',
        'group.id': 'consumer-group',
        'auto.offset.reset': 'earliest'
    }
    consumer = Consumer(consumer_conf)
    consumer.subscribe(['streamingTopic'])
    return consumer


# Streams video data received from Kafka
# Continuously polls for new messages from Kafka.
# If a message contains video frame data, it is yielded as part of a streaming response.

def generate_video_stream():
    consumer = create_kafka_consumer()
    try:
        while True:
            msg = consumer.poll(1.0)
            if msg is None:
                continue
            if msg.error():
                if msg.error().code() == KafkaError._PARTITION_EOF:
                    continue
                else:
                    print(msg.error())
                    break

            frame = msg.value()
            if frame:
                yield (b'--frame\r\n'
                       b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')
            else:
                time.sleep(0.01)
    finally:
        consumer.close()

#setup web page
@app.route('/')
def home():

    return '''
    <html>
    <head>
        <title>Video Stream</title>
    </head>
    <body>
        <h1>Stream Desktop</h1>
        <img src="/video_feed" width="1600" height="900" />
    </body>
    </html>
    '''


@app.route('/video_feed')
def video_feed():
    return Response(generate_video_stream(), mimetype='multipart/x-mixed-replace; boundary=frame')


if __name__ == '__main__':
    app.run(debug=True)
