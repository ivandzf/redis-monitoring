exports.displayNotification = (type, message) => {
  if (type === 'success') {
    this.$snotify.success(message);
  } else if (type === 'warning') {
    this.$snotify.warning(message);
  } else if (type === 'danger') {
    this.$snotify.error(message);
  }
};
