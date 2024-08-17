const storagePrefix = 'ggstudy_';
const storage = {
  set: (key: string, value: any) => {
    localStorage.setItem(storagePrefix + key, JSON.stringify(value));
  },
  get: (key: string) => {
    const value = localStorage.getItem(storagePrefix + key);
    return value ? JSON.parse(value) : null;
  },
  remove: (key: string) => {
    localStorage.removeItem(storagePrefix + key);
  },
  clear: () => {
    localStorage.clear();
  },
};

export default storage;
