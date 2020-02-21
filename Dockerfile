FROM python:latest
LABEL maintainer="dy1ng"
RUN mkdir -p code/tmp
ADD *.py code/
WORKDIR code/
CMD ["python", "__init__.py"]