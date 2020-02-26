FROM python:latest
LABEL maintainer="dy1ng"
RUN mkdir -p code/
ADD __init__.py code/
WORKDIR code/
CMD ["python", "__init__.py"]
